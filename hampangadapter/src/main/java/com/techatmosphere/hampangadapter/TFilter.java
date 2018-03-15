package com.techatmosphere.hampangadapter;

import android.widget.Filter;

import java.util.List;

/**
 * @author nirwannursabda
 * @date 14/03/18
 */

public abstract class TFilter extends Filter{
    public abstract void updateFilterItem(List listItemAll);
}