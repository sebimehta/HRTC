package com.example.user.hrtc;

public class SuggestGetSet {

    String Location_Id,Location_Name ;

    public SuggestGetSet(String Location_Id, String Location_Name)
    {
        this.setId(Location_Id);
        this.setName(Location_Name);
    }

    public void setId(String Location_Id)
    {
        this.Location_Id = Location_Id;}


    public String getName()
    {
        return Location_Name;
    }


    public void setName(String Location_Name)
    {
        this.Location_Name = Location_Name;}
}
