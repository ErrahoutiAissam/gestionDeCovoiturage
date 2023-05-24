function validateInputs(inputNames) {
    for (let i = 0; i < inputNames.length; i++) {
        const inputName = inputNames[i];
        const inputElement = document.getElementsByName(inputName)[0];

        if (inputElement.value === '') {
            alert('Input ' + inputName + ' is required!');
            return false;
        }
    }

    return true;
}
