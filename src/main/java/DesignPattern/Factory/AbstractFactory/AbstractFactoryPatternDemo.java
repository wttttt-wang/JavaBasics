package DesignPattern.Factory.AbstractFactory;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-04
 * Time: 17:01
 */

/**
 * Abstract Factory: 可以围绕一个超级工厂创建其他工厂。(该超级工厂就是工厂的工厂)
 * 使用场景: 系列的产品有多于一个产品族,而系统只消费其中某一族的产品。
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        // 1. get the shape factory
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        // 2. get "circle" using shapeFactory
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        // 1. get the color factory
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
        Color color1 = colorFactory.getColor("RED");
        color1.fill();
    }
}

// abstract factory for Shape and Color
abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}


// concrete factory1
class ShapeFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shapeType) {
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}

// concrete factory2
class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}


// factory producer: 工厂创造器/生成器类，通过传递形状或颜色信息来获取工厂
class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}

interface Shape {
    void draw();
}

// concrete shape 1
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

// concrete shape 2
class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}

// concrete shape 3
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}


interface Color {
    void fill();
}

// concrete color1
class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}

// concrete color2
class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
