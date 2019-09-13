window.onload = function () {

    document.getElementById("output").addEventListener("load", start("/jpareststarter/api/jokes/all"));


    function getBy(url) {
        fetch(url)
                .then(res => res.json()) //in flow1, just do it
                .then(data => {
                    document.getElementById("output").innerHTML =
                            "<tr><td>" + data.joke + '</td>' +
                            "<td>" + data.type + '</td></tr>';
                });
    }



    function start(url) {
        fetch(url)
                .then(res => res.json()) //in flow1, just do it
                .then(data => {
                    document.getElementById("output").innerHTML =
                            data.map(x =>
                                "<tr><td>" + x.joke + '</td>' +
                                        "<td>" + x.type + '</td></tr>');
                });
    }




    document.getElementById("fetchJoke").onsubmit = function (e) {
        e.preventDefault();
        var ID = document.getElementById("field").value;
        console.log(ID);
        getBy("/jpareststarter/api/jokes/" + Number(ID));
    }

    //document.getElementById("fetch").addEventListener("click", getBy("/jpareststarter/api/jokes/random"));

}



