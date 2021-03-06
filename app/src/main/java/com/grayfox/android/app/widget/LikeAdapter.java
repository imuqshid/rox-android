/*
 * Copyright 2014-2015 Daniel Pedraza-Arcega
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.grayfox.android.app.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grayfox.android.app.R;
import com.grayfox.android.app.util.ColorTransformation;
import com.grayfox.android.client.model.Category;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.ViewHolder> {

    private final List<Category> categories;

    public LikeAdapter(Category... categories) {
        this.categories = new ArrayList<>(Arrays.asList(categories));
    }

    public boolean add(Category category) {
        return !categories.contains(category) && categories.add(category);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.like_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Context context = holder.itemView.getContext();
        holder.likeNameTextView.setText(categories.get(position).getName());
        Picasso.with(context)
                .load(categories.get(position).getIconUrl())
                .transform(new ColorTransformation(context.getResources().getColor(R.color.secondary_text)))
                .placeholder(R.drawable.ic_generic_category)
                .into(holder.likeImageView);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView likeImageView;
        private TextView likeNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            likeImageView = (ImageView) itemView.findViewById(R.id.like_image);
            likeNameTextView = (TextView) itemView.findViewById(R.id.like_name);
        }
    }
}