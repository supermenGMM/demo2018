package com.mm.annotation.test;

@MyAnnotation(value = "aa",color = "yellow",colorEnum = ColorEnum.RED,arrayAttr = 3)
public class AnnotationUse {
    private String annotationField;
    public static void main(String[] args){
        if (AnnotationUse.class.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation myAnnotation = AnnotationUse.class.getAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation);
            System.out.println(myAnnotation.color());
            System.out.println(myAnnotation.arrayAttr());
            System.out.println(myAnnotation.colorEnum());
            System.out.println(myAnnotation.value());
        }
    }
}
