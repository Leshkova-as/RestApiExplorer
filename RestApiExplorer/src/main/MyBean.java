package main;

public class MyBean {

    private String name;
    private String type;

    public MyBean(){}

    public MyBean(String name, String type) {
        this.name = name;
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "name: " + this.name + ", type: " + type + "\n";
    }
}