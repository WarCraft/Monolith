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

package gg.warcraft.monolith.api.item

class ItemReader {

}

/*
public class SimpleItemReader implements ItemReader {
    private final StringUtils stringUtils;
    private final Item item;

    @Inject
    public SimpleItemReader(StringUtils stringUtils,
                            @Assisted @Nullable Item item) {
        this.stringUtils = stringUtils;
        this.item = item;
    }

    @Override
    public String getType() {
        if (item == null) {
            return null;
        }

        List<String> lore = item.getTooltip();
        if (lore.size() == 0) {
            return null;
        }

        if (lore.size() == 1 || stringUtils.removeChatCodes(lore.get(1)).isEmpty()) {
            return stringUtils.removeChatCodes(lore.get(0));
        }
        return null;
    }

    @Override
    public int getAttribute(Attribute attribute) {
        if (item == null) {
            return 0;
        }

        List<String> lore = item.getTooltip();
        for (String line : lore) {
            if (line.contains(attribute.getName())) {
                String rawLine = stringUtils.removeChatCodes(line);
                String onlyNumbers = rawLine.replaceAll("[\\D]", "");
                try {
                    return Integer.parseInt(onlyNumbers);
                } catch (NumberFormatException ex) {
                    return 0;
                }
            }
        }
        return 0;
    }
}

 */