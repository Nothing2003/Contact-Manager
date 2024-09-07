
const baseurl="http://localhost:8080";
// const baseurl="http://contactmanager.ap-south-1.elasticbeanstalk.com";
const viewContactsModel=document.getElementById('view_contact');

const options1 = {
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

const instanceOptions1 = {
  id: 'view_contact',
  override: true
};
const modal=new Modal(viewContactsModel,options1,instanceOptions1);
function openContactModel(){
    modal.show();
}
async function loadContactData(id) {
        try {
            const data=await (await fetch(`${baseurl}/api/contacts/${id}`)).json()
     
            document.getElementById('contact_name').innerHTML = data.name || "N/A";
            document.getElementById('contact_email').innerHTML = data.email || "N/A";
            document.getElementById('contact_image').src = data.pic || "/images/image.png";
            document.getElementById('contact_phone').innerHTML = data.phoneNumber || "N/A";
            document.getElementById('contact_address').innerHTML = data.address || "None";
            document.getElementById('contact_description').innerHTML = data.description || "None";
    
            document.getElementById('contact_web').href = data.websiteLink || "#";
            document.getElementById('contact_web_text').innerHTML = data.websiteLink || "None";
    
            document.getElementById('contact_link').href = data.LinkedInLink || "#";
            document.getElementById('contact_link_text').innerHTML = data.LinkedInLink || "None";
    
        openContactModel();
        } catch (error) {
            console.log("Error : ",error);
        }
    }
    const deleteContactModel = document.getElementById('delete_contact');

    const options2 = {
        placement: 'bottom-right',
        backdrop: 'dynamic',
        backdropClasses: 'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
        closable: true,
        onHide: () => {},
        onShow: () => {},
        onToggle: () => {},
    };
    
    const instanceOptions2 = {
        id: 'delete_contact',
        override: true
    };
    
    // Assuming the Modal library allows merging of these options
    const modal2 = new Modal(deleteContactModel, options2,instanceOptions2);
    
    function openContactModel2() {
        modal2.show();
    }
    
    async function deleteContactData(id) {  // Corrected function name
        try {
            const response = await fetch(`${baseurl}/api/contacts/${id}`);
            const data = await response.json();
    
            // Update the modal with contact data
            document.getElementById('contact_name1').innerHTML = data.name || "N/A";
            document.getElementById('contact_email1').innerHTML = data.email || "N/A";
            document.getElementById('contact_image1').src = data.pic || "/images/image.png";
            document.getElementById('contact_phone1').innerHTML = data.phoneNumber || "N/A";
            document.getElementById('contact_address1').innerHTML = data.address || "None";
            document.getElementById('contact_description1').innerHTML = data.description || "None";
    
            document.getElementById('contact_web1').href = data.websiteLink || "#";
            document.getElementById('contact_web_text1').innerHTML = data.websiteLink || "None";
    
            document.getElementById('contact_link1').href = data.LinkedInLink || "#";
            document.getElementById('contact_link_text1').innerHTML = data.LinkedInLink || "None";
            document.getElementById('id').value = data.contactId;
            // Show the modal after data is loaded
            openContactModel2();
        } catch (error) {
            console.log("Error:", error);
        }
    }
    async function deleteContact() {
        try {
            const id = document.getElementById('id').value;
            console.log("Deleting contact with ID:", id);
            await (await fetch(`${baseurl}/api/contacts/delete/${id}`));
            window.location.replace(`${baseurl}/user/contacts`)
    
            
        } catch (error) {
            console.log("Error:", error);
        }
    }