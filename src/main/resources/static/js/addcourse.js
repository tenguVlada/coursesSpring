function save_course(){
    document.getElementById("course_title_form").setAttribute("value", document.getElementById("edit_input_title_id").innerHTML.replace("'", "|"));
    document.getElementById("theme_form").setAttribute("value", document.getElementById("edit_input_text").innerHTML.replace("'", "|"));
    document.getElementById("description_form").setAttribute("value", document.getElementById("edit_description_id").innerHTML.replace("'", "|"));
    document.getElementById("data_send").submit();
};

function openPopUpLess(){
    var backPopUp = document.getElementById("popupconfless");
    var popUp = document.getElementById("popupless");
    backPopUp.style.display = "inline";
    popUp.style.display = "inline";
};

function closePopUpLess(){
    var backPopUp = document.getElementById("popupconfless");
    var popUp = document.getElementById("popupless");
    backPopUp.style.display = "none";
    popUp.style.display = "none";
};