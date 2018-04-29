package com.example.jaanfdo.myfinalproject.TableView;

import android.content.Context;
import android.widget.Toast;


import com.example.jaanfdo.myfinalproject.TableView.mDB.DBAdapter2;
import com.example.jaanfdo.myfinalproject.TableView.mModel.Spacecraft;

import java.util.ArrayList;


/**
 * TABLE HELPER CLASS. GETS ARRAYLIST FROM SQLITE DATABASE AND RETURNS A MULTIDIMENSIONAL ARRAY FOR BINDING TO OUR ADAPTER
 */
public class TableHelper {

    //DECLARATIONS
    Context c;
    private String[] spaceProbeHeaders={"Name","Propellant","Destination"};
    private String[][] spaceProbes;

    //CONSTRUCTOR
    public TableHelper(Context c) {
        this.c = c;
    }

    //RETURN TABLE HEADERS
    public String[] getSpaceProbeHeaders()
    {
        return spaceProbeHeaders;
    }

    //RETURN TABLE ROWS
    public  String[][] getSpaceProbes()
    {
        ArrayList<Spacecraft> spacecrafts=new DBAdapter2(c).retrieveSpacecrafts();
        Spacecraft s;

        spaceProbes= new String[spacecrafts.size()][3];

        for (int i=0;i<spacecrafts.size();i++) {

             s=spacecrafts.get(i);

            spaceProbes[i][0]=s.getName();
            spaceProbes[i][1]=s.getPropellant();
            spaceProbes[i][2]=s.getDestination();
        }

        return spaceProbes;
    }
}





