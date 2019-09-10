window.onload = function () {

    var btn = this.document.getElementById("refresh");
    btn.onclick = function () {
        start("http://127.0.0.1:8080/jpareststarter/api/groupmembers/all");
    }
    function start(url) {
        fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                document.getElementById("output").innerHTML =
                data.map(x =>
                    "<tr><td>" + x.name + '</td>' +
                    "<td>" + x.studentID + '</td>' +
                    "<td>" + x.color + '</td></tr>')
            })
    }

}

