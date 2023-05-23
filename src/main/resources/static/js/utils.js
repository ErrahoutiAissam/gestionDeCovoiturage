function clearFields(ids) {
    ids.forEach(id => document.getElementById(id).value = "")
}


document.getElementById("clear").addEventListener("click",() =>{

    clearFields();
})



