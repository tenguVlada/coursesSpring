var less= "";
function closePopUp(){
    var backPopUp = document.getElementById("popupcont");
    var popUp = document.getElementById("popup");
    backPopUp.style.display = "none";
    popUp.style.display = "none";
};

function openPopUp(){
    var backPopUp = document.getElementById("popupcont");
    var popUp = document.getElementById("popup");
    backPopUp.style.display = "inline";
    popUp.style.display = "inline";
};

function pageRedirect(ref){
    window.location.href = ref;
};

function pageRedirectLecture(ref){
    window.location.href = ref+less;
};

function getValue(id){
    return document.getElementById(id);
};

function updateCourse(){
    document.getElementById("courseTitle").setAttribute("value",
        document.getElementById("courseInfoTitle").innerHTML
            .replace("'", "|"));
    document.getElementById("courseTheme").setAttribute("value",
        document.getElementById("themeOfCourse").innerHTML
            .replace("'", "|"));
    document.getElementById("courseDescription").setAttribute("value",
        document.getElementById("courseDesc").innerHTML
            .replace("'", "|"));
    document.getElementById("data_send1").submit();
};

function save_course(){
    document.getElementById("course_title_form").setAttribute("value",
             document.getElementById("edit_input_title_id").innerHTML.replace("'", "|"));
    document.getElementById("theme_form").setAttribute("value",
        document.getElementById("edit_input_text").innerHTML.replace("'", "|"));
    document.getElementById("description_form").setAttribute("value",
        document.getElementById("edit_description_id").innerHTML.replace("'", "|"));
    document.getElementById("data_send").submit();
}

function closePopUpConf(){
    var backPopUp = document.getElementById("popupconfcont");
    var popUp = document.getElementById("popupconf");
    backPopUp.style.display = "none";
    popUp.style.display = "none";
};

function openPopUpLess(id){
    less = id;
    var backPopUp = document.getElementById("popupconfless");
    var popUp = document.getElementById("popupless");
    backPopUp.style.display = "inline";
    popUp.style.display = "inline";
};

function openPopUpLessEx(){
    var backPopUp = document.getElementById('popupconfless');
    var popUp = document.getElementById('popupless');
    backPopUp.style.display = "inline";
    popUp.style.display = "inline";
};

function closePopUpLess(){
    var backPopUp = document.getElementById("popupconfless");
    var popUp = document.getElementById("popupless");
    backPopUp.style.display = "none";
    popUp.style.display = "none";
};

function openPopUpConf(){
    var backPopUp = document.getElementById("popupconfcont");
    var popUp = document.getElementById("popupconf");
    backPopUp.style.display = "inline";
    popUp.style.display = "inline";
};

function statusPressed(button){
    if (button == "confirm"){
        return true;
    }
    else{
        return false;
    }
};

