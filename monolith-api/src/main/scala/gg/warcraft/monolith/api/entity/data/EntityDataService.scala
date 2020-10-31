/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.entity.data

import java.util.UUID
import java.util.logging.Logger

import gg.warcraft.monolith.api.core.Codecs.Quill._
import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.entity.team.TeamService

import scala.concurrent.ExecutionContext

class EntityDataService(implicit
    logger: Logger,
    context: ExecutionContext,
    repository: EntityDataRepository,
    eventService: EventService,
    teamService: TeamService
) {
  // initialize entity data, as players are stored separately all entities are
  // expected to not spawn often or to be short lived and not clog the data map.
  private var _data: Map[UUID, EntityData] = repository.all
  def data: Map[UUID, EntityData] = _data

  private[entity] def setEntityData(data: EntityData): Unit = {
    _data += (data.id -> data)
    repository.save(data)
  }

  private[entity] def deleteEntityData(id: UUID): Unit = {
    _data -= id
    repository.delete(id)
  }
}
