package house;

interface Predator {
    String getFood();
}

interface Barkable {
    void bark();
}

interface BarkablePredator extends Predator, Barkable {

}

class Animal {
    String name;

    void setName(String name) {
        this.name = name;
    }
}


class Dog extends Animal {
    Dog() {

    }
    void sleep() {
        System.out.println(this.name + "  zzzz...");
    }
}

class HouseDog extends Dog {
    HouseDog(String name) {
        this.setName(name);
    }

    HouseDog(int type) {
        if (type == 1) {
            this.setName("yorkshire");
        } else if (type == 2) {
            this.setName("bulldog");
        }
    }
    void sleep(int hour) {
        System.out.println(this.name + "  zzzz in house..." + hour + " hours");
    }
}

class Tiger extends Animal implements BarkablePredator {
    public String getFood() {
        return "apple";
    }

    public void bark() {
        System.out.println("어흥");
    }
}

class Lion extends Animal implements BarkablePredator {
    public String getFood() {
        return "banana";
    }
    public void bark() {
        System.out.println("으르렁");
    }
}

class ZooKeeper {
    void feed(Predator predator) {
        System.out.println("feed " + predator.getFood());
    }
}

class Bouncer {
    //    void barkAnimal(house.Animal animal) {
//        if (animal instanceof house.Tiger) {
//            System.out.println("어흥");
//        } else if (animal instanceof house.Lion) {
//            System.out.println("으르렁");
//        }
//    }
    void barkAnimal(BarkablePredator animal) {
        animal.bark();
    }
}

public class Sample {
    public static void main(String[] args) {
//        house.Animal cat = new house.Animal();0
//        cat.setName("bobby");
//
//        house.Animal dog = new house.Animal();
//        dog.setName("happy");
//
//        System.out.println(cat.name);
//        System.out.println(cat.name);System.out.println(cat.name);
//        System.out.println(dog.name);
//        house.Dog dog = new house.Dog();
//        dog.setName("poppy");
//        System.out.println(dog.name);
//        dog.sleep();

//        house.HouseDog houseDog = new house.HouseDog();
//        houseDog.setName("happy");
//        houseDog.sleep();
//        houseDog.sleep(3);

//        house.HouseDog dog = new house.HouseDog("happy");
//        System.out.println(dog.name);
//
//        house.HouseDog houseDog = new house.HouseDog(2);
//        System.out.println(houseDog.name);
//        house.ZooKeeper zooKeeper = new house.ZooKeeper();
//        house.Tiger tiger = new house.Tiger();
//        house.Lion lion = new house.Lion();
//        zooKeeper.feed(tiger);
//        zooKeeper.feed(lion);
        Tiger tiger = new Tiger();
        Lion lion = new Lion();

        Bouncer bouncer = new Bouncer();
        bouncer.barkAnimal(tiger);
        bouncer.barkAnimal(lion);

    }
}