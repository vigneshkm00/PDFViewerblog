package pdfviewer.pdfviewer;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView grid1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid1 = (GridView) findViewById(R.id.book_grid);
        grid1.setAdapter(new VivzAdapter(this));
        grid1.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this,pdf_viewer.class);
        i.getStringExtra(String.valueOf(position).toString());
        startActivity(i);
        Toast.makeText(getApplicationContext(),"book Opened"+(position+1),Toast.LENGTH_LONG).show();
    }
}
class Book
{
    int imageId;
    String bookName;
    Book(int imageId,String bookName)
    {
        this.imageId=imageId;
        this.bookName=bookName;
    }

}
class VivzAdapter extends BaseAdapter
{
    ArrayList<Book> list;
    Context context;
    VivzAdapter(Context context)
    {
        this.context=context;
        list=new ArrayList<Book>();
        Resources res=context.getResources();
        String[] book_array = res.getStringArray(R.array.book_name);
        int[] book_img = {R.drawable.image,R.drawable.cover,R.drawable.image,R.drawable.cover,R.drawable.image,R.drawable.cover,R.drawable.image,R.drawable.cover,R.drawable.image,R.drawable.image};
        for (int i =0;i<10;i++)
        {
            Book tempbook = new Book(book_img[i],book_array[i]);
            list.add(tempbook);
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        if(row==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_book,parent,false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        Book temp=list.get(position);
        holder.myBook.setImageResource(temp.imageId);
        holder.myBook_name.setText(temp.bookName);
        return row;
    }
    class ViewHolder
    {
        ImageView myBook;
        TextView myBook_name;
        ViewHolder(View v)
        {
            myBook=(ImageView) v.findViewById(R.id.img_view);
            myBook_name= (TextView) v.findViewById(R.id.textView);
        }
    }
}
