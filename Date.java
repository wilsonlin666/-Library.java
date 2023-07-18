public class Date {
     private int day,month,year;

    public Date(){}

    public Date(int day,int month, int year) throws IllegalArgumentException{
        super();
        if(month <= 12 && month > 0 && day > 0 && day <= 31){
            this.day = day;
            this.month = month;
            this.year = year;
        }
        else{
            throw new IllegalArgumentException("Invalid Date Entered");
        }
    }

    public int getDay(){
        return this.day;
    }

    public void setDay(int day){
        this.day = day;
    }

    public int getMonth(){
        return this.month;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public int getYear(){
        return this.year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public static int compare(Date x, Date y){
        Integer year1 = x.getYear();
        Integer year2 = y.getYear();
        if(year1.compareTo(year2) == 0){
            Integer month1 = x.getMonth();
            Integer month2 = y.getMonth();
            if(month1.compareTo(month2) == 0){return Integer.compare(x.getDay(), y.getDay());}
            else{return month1.compareTo(month2);}
        }else
        {
            return year1.compareTo(year2);
        }
    }

    public boolean after(Date other){
        if(this.year > other.year){
            return true;
        }else if(this.year == other.year){
            if(this.month > other.month){
                return true;
            }else if(this.month == other.month){
                return this.day > other.day;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    public static void main(String...args){
       // Date date0 = new Date(32,4,2002);
        Date date1 = new Date(10, 4,2002);
        Date date2 = new Date(10,5,2002);
        Date date3 = new Date(10,4,2002);
        System.out.println(date1);
        System.out.println(compare(date1,date2));
        System.out.println(compare(date2,date1));
        System.out.println(compare(date1,date3));
    }
}
