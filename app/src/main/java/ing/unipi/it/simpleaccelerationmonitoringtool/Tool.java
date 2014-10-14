package ing.unipi.it.simpleaccelerationmonitoringtool;

import android.widget.ToggleButton;

/**
 * Created by carmen on 13/10/14.
 */
public class Tool {
   int icon;
    String name;



    public Tool(/*int icon,*/ String name) {

        //this.icon = icon;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
