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