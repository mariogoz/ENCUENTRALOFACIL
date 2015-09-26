/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function limpiarRegistro()
{
    var rut = document.getElementsByName("txtRutRegistro").value;
    var dv = document.getElementsByName("txtRutDvRegistro").value;
    var primerNombre = document.getElementsByName("txtPrimerNombreRegistro").value;
    var segundoNombre = document.getElementsByName("txtSegundoNombreRegistro").value;
    var apellidoPaterno = document.getElementsByName("txtPrimerApellRegistro").value;
    var apellidoMaterno = document.getElementsByName("txtSegundoApelliRegisto").value;
    var fecNacimiento = document.getElementsByName("dateFechaNacRegistro").value;

    rut.clear();

}

function validar1(formulario) {
    var rut1 = formulario.rut1.value;
    var count = 0;
    var count2 = 0;
    var factor = 2;
    var suma = 0;
    var sum = 0;
    var digito = 0;
    count2 = rut1.length - 1;
    while (count < rut1.length) {

        sum = factor * (parseInt(rut1.substr(count2, 1)));
        suma = suma + sum;
        sum = 0;

        count = count + 1;
        count2 = count2 - 1;
        factor = factor + 1;

        if (factor > 7) {
            factor = 2;
        }

    }
    digito = 11 - (suma % 11);

    if (digito == 11) {
        digito = 0;
    }
    if (digito == 10) {
        digito = "k";
    }
    form.dv1.value = digito;
}

function mis_datos() {
    var key = window.event.keyCode;
    if (key < 48 || key > 57) {
        window.event.keyCode = 0;
    }
}

function vaciar(control)
{
    control.value = '';
}




