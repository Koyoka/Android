//package com.yrkj.elderlycareassess;
//<?xml version="1.0" encoding="utf-8"?>  
//<resources>  
//  
//    <!-- Base application theme is the default theme. -->  
//    <style name="Theme" parent="android:Theme"></style>  
//  //    <!-- Variation on the Holo Light theme that styles the Action Bar -->  
//    <!-- style的名字是Theme.AndroidDevelopers，父类为Theme.Holo.Light，即白底黑字（如果是Theme.Holo，则为黑底白字）-->  
//    <style name="Theme.AndroidDevelopers" parent="android:style/Theme.Holo.Light">  
//        <!-- 这是items的颜色变化，未选中时为白色，选中时为绿色，逻辑请见ad_selectable_background.xml -->  
//        <item name="android:selectableItemBackground">@drawable/ad_selectable_background</item>  
//        <!-- 其他部分的颜色变化跟上面类似 -->  
//        <item name="android:popupMenuStyle">@style/MyPopupMenu</item>  
//        <item name="android:dropDownListViewStyle">@style/MyDropDownListView</item>  
//        <item name="android:actionBarTabStyle">@style/MyActionBarTabStyle</item>  
//        <item name="android:actionDropDownStyle">@style/MyDropDownNav</item>  
//        <item name="android:listChoiceIndicatorMultiple">@drawable/ad_btn_check_holo_light</item>  
//        <item name="android:listChoiceIndicatorSingle">@drawable/ad_btn_radio_holo_light</item>  
//        <!--<item name="android:actionOverflowButtonStyle">@style/MyOverflowButton</item>-->  
//    </style>  
//      
//    <!-- style the overflow menu -->  
//    <style name="MyPopupMenu" parent="android:style/Widget.Holo.Light.ListPopupWindow">  
//        <item name="android:popupBackground">@drawable/ad_menu_dropdown_panel_holo_light</item>   
//    </style>  
//      
//    <!-- style the items within the overflow menu -->  
//    <style name="MyDropDownListView" parent="android:style/Widget.Holo.ListView.DropDown">  
//        <item name="android:listSelector">@drawable/ad_selectable_background</item>  
//    </style>  
//  
//    <!-- style for the tabs -->  
//    <style name="MyActionBarTabStyle" parent="android:style/Widget.Holo.Light.ActionBarView_TabView">  
//        <item name="android:background">@drawable/actionbar_tab_bg</item>  
//        <item name="android:paddingLeft">32dp</item>  
//        <item name="android:paddingRight">32dp</item>  
//    </style>  
//      
//    <!-- style the list navigation -->  
//    <style name="MyDropDownNav" parent="android:style/Widget.Holo.Light.Spinner.DropDown.ActionBar">  
//        <item name="android:background">@drawable/ad_spinner_background_holo_light</item>  
//        <item name="android:popupBackground">@drawable/ad_menu_dropdown_panel_holo_light</item>  
//        <item name="android:dropDownSelector">@drawable/ad_selectable_background</item>  
//    </style>  
//  
//    <!-- the following can be used to style the overflow menu button  
//         only do this if you have an *extremely* good reason to!! -->  
//    <!--<style name="MyOverflowButton" parent="@android:style/Widget.Holo.ActionButton.Overflow">  
//        <item name="android:src">@android:drawable/ic_menu_view</item>  
//        <item name="android:background">@drawable/action_button_background</item>  
//    </style>-->  
//  
//</resources> 