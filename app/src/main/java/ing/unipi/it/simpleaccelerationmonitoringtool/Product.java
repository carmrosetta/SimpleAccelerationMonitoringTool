package ing.unipi.it.simpleaccelerationmonitoringtool;


/**
 * Created by carmen on 14/10/14.
 */
public class Product {

    String name;

    public Product(String name) {

// TODO Auto-generated constructor stub

        this.name = name;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    @Override

    public String toString() {

// TODO Auto-generated method stub

        return this.name;

    }

}

