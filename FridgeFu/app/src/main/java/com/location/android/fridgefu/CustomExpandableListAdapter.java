package com.location.android.fridgefu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<FridgeItem>> expandableListDetail;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<FridgeItem>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        final FridgeItem expandedListObject = (FridgeItem) getChild(listPosition, expandedListPosition);
        final String expandedListText = expandedListObject.ingredient;
        final String expandedListExpiration = sdf.format(expandedListObject.expiration_date.getTime());

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        View inner = (LinearLayout) convertView.findViewById(R.id.fridgeInnerLinearLayout);
        LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) inner.getLayoutParams();

        float dip = 140f;
        if (!expandedListObject.show_settings) {
            dip = 0f;
        }

        Resources r = parent.getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        p.setMargins(p.leftMargin, p.topMargin, (int)px, p.bottomMargin);
        inner.setLayoutParams(p);

//        Log.e("DATE 1", Long.toString(expandedListObject.expiration_date.getTimeInMillis()));
//        Log.e("DATE 2", Long.toString(exd.getTimeInMillis()));
//        Log.e("DATE 2", Long.toString( (expandedListObject.expiration_date.getTimeInMillis() - (exd.getTimeInMillis())) ));

        String[] strArray = expandedListText.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap + " ");
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(builder);

        TextView expandedListDateView = (TextView) convertView
                .findViewById(R.id.expandedListItemExpiration);
        expandedListDateView.setText(expandedListExpiration);

        TextView exclamation = (TextView) convertView.findViewById(R.id.expandedListItemExclamation);
        if (expandedListObject.is_expired) {
//            exclamation.setBackgroundColor(0xff700000);
            exclamation.setAlpha(1f);
        } else {
            exclamation.setAlpha(0f);
//            exclamation.setBackgroundColor(0x00000000);
        }

//        convertView.setBackgroundColor(getFoodGroupColor((String) getGroup(listPosition) + "_child"));


        Button deleteButton = convertView.findViewById(R.id.expandedListItemDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableListDetail.get(expandableListTitle.get(listPosition))
                        .remove(expandedListPosition);
                notifyDataSetChanged();
            }
        });




        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
//        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle.substring(0,1).toUpperCase() + listTitle.substring(1));
//        listTitleTextView.setBackgroundColor(getFoodGroupColor(listTitle));
//        List<FridgeItem> fridgeItemList= this.expandableListDetail.get(this.expandableListTitle.get(listPosition));
//
//        List<FridgeItem> gl = (List<FridgeItem>) pair.getValue();
//        Iterator glit = gl.iterator();
//        while (glit.hasNext()) {
//            GroceryItem glpair = (GroceryItem) glit.next();
//            if (glpair.is_bought) {
//                fexpandableListDetail.get(group).add(new FridgeItem(glpair.ingredient));
//
//                if (!glpair.is_pinned) {
//                    glit.remove(); // avoids a ConcurrentModificationException
//                } else {
//                    glpair.is_bought = false;
//                }
//            }
//        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


    public int getFoodGroupColor(String group) {
        int b = context.getResources().getIdentifier(group, "color", context.getPackageName());
        int a = ContextCompat.getColor(context, b);
        return a;
    }
}
