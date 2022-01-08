
async function postFormDataAsJson({ url, obj }) {
    //const plainFormData = Object.fromEntries(formData.entries());
    const jsonString = JSON.stringify(obj);

    const fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            'Authorization': 'Bearer <token>'
        },
        body: jsonString,
    };

    const response = await fetch(url, fetchOptions);

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }

    return response.json();
}


/** Event handler per al form submit event. */
async function handleFormSubmit(event) {
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;

    /** Recuperació de valors del formulari */
    var productes = [];
    var ingredients = document.getElementsByName('ingredient[]');
    var quantitats = document.getElementsByName('quantitat[]');
    var mesures = document.getElementsByName('mesura[]');
    for(var i=0; i<ingredients.length; i++){
        if(ingredients[i].value.length > 0){
            productes.push([ingredients[i].value, quantitats[i].value, mesures[i].value]);
        }
    }
    var steps = [];
    var pasos = document.getElementsByName('steps[]');
    for(var j=0; j<pasos.length; j++) {
        if (pasos[j].value.length > 0){
            steps.push(pasos[j].value);
        }
    }

    /** Recuperació valors del formulari i Construcció del JSON Object **/
    var obj = new Object();
    obj.user = document.getElementById('user').value;
    obj.date = document.getElementById('date').value;
    obj.name = document.getElementById('name').value;
    obj.description  = document.getElementById('descripcio').value;
    obj.category = document.querySelector('#categoria').value;
    obj.duration = document.getElementById('temps').value;
    obj.dificulty = document.querySelector('#dificultat').value;
    obj.people = document.getElementById('comensals').value;
    obj.picture = document.getElementById('arxiuFoto').value;
    obj.steps = [];
    for(var x=0; x<steps.length; x++) {
        var step = {"id":x, "step":steps[x]};
        obj.steps.push(step);
    }
    obj.products = [];
    for (var y=0; y<productes.length; y++) {
        var prod = {"name": productes[y][0], "quantity": productes[y][1], "unit": productes[y][2]}
        obj.products.push(prod);
    }

    //const jsonString= JSON.stringify(obj);
    //console.log(obj);

    try {
        //const formData = new FormData(form);
        const responseData = await postFormDataAsJson({ url, obj });

        console.log({ responseData });
    } catch (error) {
        console.error(error);
    }
}

const recipeForm = document.getElementById('recipe-form');
recipeForm.addEventListener("submit", handleFormSubmit);






