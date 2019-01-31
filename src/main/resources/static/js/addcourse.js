function save_course(){
    document.getElementById("course_title_form").setAttribute("value",
                                document.getElementById("edit_input_title_id").innerHTML.replace("'",
                                    "|"));
    document.getElementById("theme_form").setAttribute("value",
                                document.getElementById("edit_input_text").innerHTML.replace("'",
                                    "|"));
    document.getElementById("description_form").setAttribute("value",
                                document.getElementById("edit_description_id").innerHTML.replace("'",
                                    "|"));
    document.getElementById("data_send").submit();
};

function update_course(){
    document.getElementById("courseTitle").setAttribute("value",
                            document.getElementById("courseInfoTitle").innerHTML
                                .replace("'", "|"));
    document.getElementById("courseTheme").setAttribute("value",
                            document.getElementById("themeOfCourse").innerHTML
                                .replace("'", "|"));
    document.getElementById("courseDescription").setAttribute("value",
                            document.getElementById("loremm").innerHTML
                                .replace("'", "|"));
    document.getElementById("data_send1").submit();
};


