package gg.warcraft.monolith.api.core

trait CaseClass extends Product {
  for ((elem, i) <- productIterator.zipWithIndex) require(elem != null, {
    val product = getClass.getDeclaredFields()(i).toString.split('.').last
    s"${product} is null, must be != null"
  })

  protected def copyWith(field: String, value: Any): this.type = {
    val values = getClass.getDeclaredFields
      .map(_.toString.split('.').last)
      .zip(productIterator.toList)
      .map(it => if (it._1 == field) value else it._2)
      .map(_.asInstanceOf[Object])

    getClass.getConstructors.head
      .newInstance(values: _*)
      .asInstanceOf[this.type]
  }

  protected def copyWithConst(const: String, value: Any): this.type = {
    val copy = copyWith("", null)
    getClass.getDeclaredFields
      .find(it => it.getName.split('.').last == const)
      .foreach(it => {
        it.setAccessible(true);
        it.set(copy, value);
        it.setAccessible(false);
      })
    copy
  }
}
