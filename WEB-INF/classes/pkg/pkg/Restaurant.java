package pkg;

public class Restaurant{

    private String[] tables;

    public Restaurant(){
        this.tables = new String[10];
    }

    public String[] getTables(){
        return this.tables;
    }

    public boolean isReserved(int idx){
        if(idx > 9 || idx < 0){
            return false;
        }
        return tables[idx] != null;
    }

    public boolean book(int idx, String name){
        if(isReserved(idx)){
            return false;
        }
        tables[idx] = name;
        return true;
    }
}