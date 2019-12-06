package com.location.android.fridgefu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.List;

public class GroceryExpandableListAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<GroceryItem>> expandableListDetail;

    public GroceryExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<GroceryItem>> expandableListDetail) {
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
        final GroceryItem expandedListObject = (GroceryItem) getChild(listPosition, expandedListPosition);
        final String expandedListText = expandedListObject.ingredient;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.grocery_item, null);
        }
        View inner = (LinearLayout) convertView.findViewById(R.id.groceryInnerLinearLayout);
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


        String[] strArray = expandedListText.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap + " ");
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(builder);

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

        CheckBox boughtItem = convertView.findViewById(R.id.expandedListItemCheckMark);
        boughtItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                expandableListDetail.get(expandableListTitle.get(listPosition))
                        .get(expandedListPosition).is_bought = isChecked;
                notifyDataSetChanged();
            }
        });

        ((CheckBox)convertView.findViewById(R.id.expandedListItemCheckMark)).setChecked(expandedListObject.is_bought);

        TextView pinned = convertView.findViewById(R.id.expandedListItemPinned);
//        Log.e(expandedListObject.ingredient, Boolean.toString(expandedListObject.is_pinned));
        if (expandedListObject.is_pinned) {
            pinned.setBackgroundColor(0xff700000);
        } else {
            pinned.setBackgroundColor(0x00000000);
        }


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

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    public int getFoodGroupColor(String group) {
        int b = context.getResources().getIdentifier(group, "color", context.getPackageName());
        int a = ContextCompat.getColor(context, b);
        return a;
    }
}
