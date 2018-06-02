package pdfviewer.pdfviewer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final String[] nam;
    private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] web, Integer[] imageId,String[] nam) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.nam= nam;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.single_view, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.eng_name);
        TextView txtTitle1 = (TextView) rowView.findViewById(R.id.kana_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.genreimage);
        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.imageView2);
        txtTitle.setText(web[position]);
        txtTitle1.setText(nam[position]);
        imageView1.setImageResource(R.drawable.ic_chevron_right_black_24dp);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
