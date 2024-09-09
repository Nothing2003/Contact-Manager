const deleteModel = document.getElementById('delete_user');

const baseurl="http://localhost:8080";

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
    id: 'delete_user',
    override: true
};

const modal3 = new Modal(deleteModel, options2,instanceOptions2);

function openContactModel3() {
    modal3.show();
}

async function deleteData() {  
    try {

        openContactModel3();
    } catch (error) {
        // console.log("Error:", error);
    }
}
async function deleteAccount() {
    try {
        const id = document.getElementById('id').value;
        console.log("Deleting contact with ID:", id);
        await (await fetch(`${baseurl}/api/contacts/delete/${id}`));
        window.location.replace(`${baseurl}/user/contacts`)

        
    } catch (error) {
        // console.log("Error:", error);
    }
}