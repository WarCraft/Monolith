package gg.warcraft.monolith.api.util

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
}
