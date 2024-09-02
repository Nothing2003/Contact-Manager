

const viewContactsModel=document.getElementById('view_contact');
// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
    },
    onShow: () => {
    },
    onToggle: () => {
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact',
  override: true
};
const modal=new Modal(viewContactsModel,options,instanceOptions);
function openContactModel(){
    modal.show();
}
async function loadContactData(id) {
        try {
            const data=await (await fetch(`http://localhost:8080/api/contacts/${id}`)).json()
     
        document.getElementById('contact_name').innerHTML=data.name;
        document.getElementById('contact_email').innerHTML=data.email;
        if(data.pic==null){
            document.getElementById('contact_image').src="/images/image.png";
        }else{
            document.getElementById('contact_image').src=data.pic;
        }
        
        document.getElementById('contact_phone').innerHTML=data.phoneNumber;
        document.getElementById('contact_address').innerHTML=data.address;
        document.getElementById('contact_description').innerHTML=data.contact_description;
        openContactModel();
        } catch (error) {
            console.log("Error : ",error);
        }
}