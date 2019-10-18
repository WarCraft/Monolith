package gg.warcraft.monolith.api.core

trait JavaCaseInterop extends Product {
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
