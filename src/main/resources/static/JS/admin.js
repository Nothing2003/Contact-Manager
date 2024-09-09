
document.querySelector("#imagefileInput").addEventListener("change",function(event){
    let file=event.target.files[0];
    let reader=new FileReader();
    reader.onload=function() {
        document.querySelector("#uploadImagePreview").setAttribute("src",reader.result)
        
    };
    reader.readAsDataURL(file);
})

document.querySelector("#imagefileInput2").addEventListener("change",function(event){
    let file=event.target.files[0];
    let reader=new FileReader();
    reader.onload=function() {
        document.querySelector("#uploadImagePreview2").setAttribute("src",reader.result)
        
    };
    reader.readAsDataURL(file);
})
