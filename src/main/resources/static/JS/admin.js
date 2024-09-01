console.log("admin user")
document.querySelector("#imagefileInput").addEventListener("change",function(event){
    var file=event.target.files[0];
    let reader=new FileReader();
    reader.onload=function() {
        document.querySelector("#uploadImagePreview").setAttribute("src",reader.result)
        
    };
    reader.readAsDataURL(file);

})