package com.example.rumrunner;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;


public class FaveListFragment extends Fragment {

    private DrinkListManager listManager;
    private DrinkItemAdapter adapter;

    public FaveListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fave_list, container, false);

        ListView drinkList = (ListView) view.findViewById(R.id.drinkList);

        listManager = new DrinkListManager(this.getContext());
        adapter = new DrinkItemAdapter(this.getContext(), listManager.getItems());

        drinkList.setAdapter(adapter);

        return view;
    }

    private class DrinkItemAdapter extends ArrayAdapter<DrinkItem> {
        private Context context;
        private List<DrinkItem> items;

        public DrinkItemAdapter(Context context, List<DrinkItem> items) {
            super(context, -1, listManager.getItems());

            this.context = context;
            this.items = items;
        }
        // this function updates app to new information input in database
        public void updateItems(List<DrinkItem> items)
        {
            this.items = items;
            notifyDataSetChanged(); //notifies app that Data has changed
        }

        //Helps app realize it needs to repopulate items
        @Override
        public int getCount()
        {
            return items.size();
        }

        //This is an override of a function of the Adapter Interface
        //If it looks like it handles one row, that is because it does handle
        //one and only one row. The ListView, probably calls this a bunch of times according to the
        //amount of items you pass it on creation. item is determined by int position.
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            /*View Holder Pattern
             * This pattern recycles row items so that the list does not have to call the views
             * every time something is added     */

            final ItemViewHolder holder;

            if(convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.drink_item_layout,
                        parent,
                        false);

                holder = new ItemViewHolder();
                holder.itemName = convertView.findViewById(R.id.drinkItemTextView);
                holder.itemPic = convertView.findViewById(R.id.drinkIconImageView);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ItemViewHolder)  convertView.getTag();
            }


            //Set up the text for each row
            String text = items.get(position).getStrDrinkName();
            holder.itemName.setText(text);
            Picasso.get().load(items.get(position).getStrDrinkThumb()).into(holder.itemPic);

            //Sets Tags for the checkbox on the item
            holder.itemPic.setTag(items.get(position));

            //Image button for Deleting a row.
            ImageButton deleteButton = convertView.findViewById(R.id.deleteButton);


            /*
            This function is an onClickListener
            It is used to get a detail view of the selected Drink
            */
            View.OnClickListener onClickListener = new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    //Tried to put something here but ran out of time.

                }
            };

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDeleteButtonClick(position);
                    notifyDataSetChanged();
                }
            });


            //These lines set the row and the checkbox to activate
            // our saved onClickListener function
            convertView.setOnClickListener(onClickListener);
            holder.itemPic.setOnClickListener(onClickListener);

            return convertView;
        }

        //delete button onClick function
        private void onDeleteButtonClick(int position)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.delete_item);

            TextView question = new TextView(context);

            question.setText(R.string.confirm_delete);

            builder.setView(question);

            builder.setNegativeButton(android.R.string.cancel,
                    new DialogInterface.OnClickListener()
                    {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

            builder.setPositiveButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            listManager.removeItem(items.get(position));
                            updateItems(listManager.getItems());
                        }
                    });

            builder.show();
        }

    }

    //This class is created to hold the view information
    public static class ItemViewHolder
    {
        public TextView itemName;
        public ImageView itemPic;
    }
}