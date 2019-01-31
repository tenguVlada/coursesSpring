var imgs = [1, 0];

function save_lecture(){
    var elem = document.getElementsByClassName("edit_input_text");
    var str = "";

    var cnt_text = 0;
    var cnt_img = 0;
    for(var i = 0; i < imgs.length; i++)
    {
        if (imgs[i] == 0) {
            /*str += cnt_img + "<br>";
            cnt_img++;*/
        }
        else {
            str += elem[cnt_text].innerHTML + "<br>";
            cnt_text++;
        }
    }

    str.replace("'", "|");

    /*for(var i = 0; i < str.length; i++)
    {
        if(str.charAt(i) == '\'')
            str = '|';
    }*/
    document.getElementById("course_title_form").setAttribute("value", document.getElementById("course_title").innerHTML.replace("'", "|"));
    document.getElementById("lecture_title_form").setAttribute("value", document.getElementById("edit_input_title_id").innerHTML.replace("'", "|"));
    document.getElementById("description_form").setAttribute("value", document.getElementById("edit_description_id").innerHTML.replace("'", "|"));
    document.getElementById("text_form").setAttribute("value", str);
    document.getElementById("data_send").submit();
};

function get_text(){
    /*document.getElementById("lecture_title_id").innerHTML = sessionStorage.getItem("lecture_title");
    document.getElementById("description_id").innerHTML = sessionStorage.getItem("description");
    document.getElementById("text_id").innerHTML = sessionStorage.getItem("text");*/
};

function get_str(){
    return sessionStorage.getItem("course");
} ;

function add_input() {
 var p = document.getElementById("rightcol_id");
 var new_input = document.createElement("div");
 new_input.name = "editable_input";

 new_input.setAttribute("id", "input_id");
 new_input.setAttribute("class","edit_input_text");
 new_input.setAttribute("contenteditable","true");
 new_input.setAttribute("data-placeholder", "Enter text...");
 new_input.setAttribute("role", "textbox");
 new_input.setAttribute("aria-multiline", "true");

 p.appendChild(new_input);

 imgs.push(1);
} ;

function add_photo() {
 var p = document.getElementById("rightcol_id");
 var new_photo = document.createElement("div");
 new_photo.name = "input_photo";
 new_photo.setAttribute("class", "input_photo");

 var btn_upload = document.createElement("input");
 btn_upload.name = "btn_upload";
 btn_upload.setAttribute("id","upload");
 btn_upload.setAttribute("onchange", "readURL(this);");
 btn_upload.setAttribute("type", "file");
 /*btn_upload.innerHTML = "+ Choose photo";*/

 var img_upload = document.createElement("img");
 img_upload.name = "img_upload";
 img_upload.src = "#";
 /*img_upload.setAttribute("src", "#");*/
 img_upload.setAttribute("alt", "image");
 img_upload.setAttribute("id", "upload-img");

 new_photo.appendChild(btn_upload);
 new_photo.appendChild(img_upload);

 p.appendChild(new_photo);

 imgs.push(0);
} ;



function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('upload-img')
                    .attr('src', e.target.result)
                    .width(150)
                    .height(200);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }

function closePopUp(){
    var backPopUp = document.getElementById("popupcont");
    var popUp = document.getElementById("popup");
    backPopUp.style.display = "none";
    popUp.style.display = "none";
}

function openPopUp(){
    var backPopUp = document.getElementById("popupcont");
    var popUp = document.getElementById("popup");
    backPopUp.style.display = "inline";
    popUp.style.display = "inline";
}
