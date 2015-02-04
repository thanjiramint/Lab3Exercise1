package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;
    TextView tvGP,tvGPA, tvCR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        tvGP = (TextView) findViewById(R.id.tvGP);
        tvGPA = (TextView) findViewById(R.id.tvGPA);
        tvCR = (TextView) findViewById(R.id.tvCR);

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void buttonClicked(View v) {
        listCodes.clear();
        listCredits.clear();
        listGrades.clear();

        int cr = 0;         // Credits
        double gp = 0.0;    // Grade points
        double gpa = 0.0;   // Grade point average

        TextView tv =(TextView)findViewById(R.id.tvGPA);
        TextView tv1 =(TextView)findViewById(R.id.tvGP);
        TextView tv2 =(TextView)findViewById(R.id.tvCR);
        tv.setText(Double.toString(gpa));
        tv1.setText(Double.toString(gp));
        tv2.setText(Integer.toString(cr));

    }

    public void courseListClicked(View v) {
        Intent i = new Intent(this, CourseListActivity.class);

        String show = "";
        int index = 0;
        for(index=0;index<listCodes.size();index++) {
            show += listCodes.get(index) + " (" + listCredits.get(index) + " credits) = " + listGrades.get(index)+"\n";

        }

        i.putExtra("toList", show);
        startActivity(i);
    }

    public void courseActClicked(View v) {
        Intent i = new Intent(this, CourseActivity.class);
        startActivityForResult(i, 66);
    }

    public void calculate()
    {

        int cr = 0;         // Credits
        double gp = 0.0;    // Grade points
        double gpa = 0.0;   // Grade point average


        for(int i =0; i<listCodes.size();i++)
        {
            String s = listCodes.get(i);
            int a = listCredits.get(i);

            cr+=a;

            String rg = listGrades.get(i);
            if(rg.equals("A"))
               gp+= 4.00 * a;
            else if(rg.equals("B+"))
                gp+= 3.50 * a;
            else if(rg.equals("B"))
                gp+= 3.00 * a;
            else if(rg.equals("C+"))
                gp+= 2.50 * a;
            else if(rg.equals("C"))
                gp+= 2.00 * a;
            else if(rg.equals("D+"))
                gp+= 1.50 * a;
            else if(rg.equals("D"))
                gp+= 1.00 * a;
            else if(rg.equals("F"))
                gp+= 0.00 * a;

        }
            gpa=gp/cr;

        TextView tv =(TextView)findViewById(R.id.tvGPA);
        TextView tv1 =(TextView)findViewById(R.id.tvGP);
        TextView tv2 =(TextView)findViewById(R.id.tvCR);
        tv.setText(Double.toString(gpa));
        tv1.setText(Double.toString(gp));
        tv2.setText(Integer.toString(cr));
        //Display the result

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 66) {
        //    Toast t = Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT);
         //   t.show();
            if (resultCode == RESULT_OK) {
                listCodes.add(data.getStringExtra("sendCode"));
                listCredits.add(data.getIntExtra("sendCredit", -1));
                listGrades.add(data.getStringExtra("sendGrade"));


              // tvGP.setText(grade+"");
           //      tvCR.setText(credit+"");
                calculate();

            } else if (resultCode == RESULT_CANCELED) {

            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
