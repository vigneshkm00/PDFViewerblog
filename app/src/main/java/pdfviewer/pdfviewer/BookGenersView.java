package pdfviewer.pdfviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;


public class BookGenersView extends Activity {
    ListView list;
    String[] web = {
            "Zoology",
            "Biology",
            "Chemistry",
            "English",
    } ;
    String[] nam= {"ಪ್ರಾಣಿಶಾಸ್ತ್ರ",
            "ಜೀವಶಾಸ್ತ್ರ",
            "ರಸಾಯನಶಾಸ್ತ್ರ",
            "ಇಂಗ್ಲಿಷ್"
    };
    Integer[] imageId = {
            R.drawable.giraffe,
            R.drawable.hibis,
            R.drawable.chem,
            R.drawable.abc,


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_geners_view);


        CustomList adapter = new
                CustomList(BookGenersView.this, web, imageId,nam);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(BookGenersView.this,MainActivity.class);
                startActivity(i);
                Toast.makeText(BookGenersView.this, "You Clicked at " +web[+ position]+"/"+nam[+position], Toast.LENGTH_SHORT).show();

            }
        });




    }

}
