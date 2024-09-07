
function setTheme(theme){
    localStorage.setItem("theme",theme);
}
function getTheme(){
  let theme=localStorage.getItem("theme");
 return theme? theme: "light";
}

let current=getTheme();
document.addEventListener("DOMContentLoaded",()=>{
    changeTheme1(current);
});

function changeTheme1(){
document.querySelector('html').classList.add(current);
let change= document.querySelector('#theme_change');
change.addEventListener("click",()=>{console.log("change")
   const old=current;
    if(current=='dark'){
        current='light'
    }else{
        current='dark'
    }
    setTheme(current);
    document.querySelector('html').classList.remove(old);
    document.querySelector('html').classList.add(current);
    const text=current=='dark'? "Light":"Dark";
    change.querySelector('span').textContent=text;
    
});

}

document.addEventListener("DOMContentLoaded",()=>{
    changeTheme2(current);
});


function changeTheme2(){
    document.querySelector('html').classList.add(current);
    let change= document.querySelector('#theme_change1');
    change.addEventListener("click",()=>{console.log("change")
       const old=current;
        if(current=='dark'){
            current='light'
        }else{
            current='dark'
        }
        setTheme(current);
        document.querySelector('html').classList.remove(old);
        document.querySelector('html').classList.add(current);
        const text=current=='dark'? "Light":"Dark";
        change.querySelector('span').textContent=text;
        
    });
    
    }
    