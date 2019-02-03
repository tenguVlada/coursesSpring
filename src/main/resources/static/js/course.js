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

function doSubmit() {
    window.transer("editsavecourse.jsp");
};

function getValue(id){
    return document.getElementById(id);
};

function call(course_id, edit){
    if (edit.localeCompare("true") == 0) {
        var course_lecturer = document.getElementById("lecturerOfCourse").innerHTML;
        var course_name = document.getElementById("courseInfoTitle").innerHTML;
        var course_theme = document.getElementById("themeOfCourse").innerHTML;
        var course_description = document.getElementById("lorem").innerHTML;
        window.location.href = 'editsavecourse.jsp?course_id=' + course_id + '&edit=' + edit + '&course_lecturer=' + course_lecturer + '&course_name=' + course_name + '&course_theme=' + course_theme + '&course_description=' + course_description;
    }
    else
        window.location.href = 'editsavecourse.jsp?course_id=' + course_id + '&edit=' + edit;
};

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