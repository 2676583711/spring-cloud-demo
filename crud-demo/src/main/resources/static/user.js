$(function () {
    load();
});

function isNull(str) {
    if (str == null || str == undefined || str == '') {
        return '-';
    }
    return str;
}

function splicingResult(result) {
    let trtd = '';
    result.forEach(user => {
        trtd += '        <tr>' +
            '            <td>' +
            isNull(user.username) +
            '            <td>' +
            '            <td>' +
            isNull(user.nickname) +
            '            <td>' +
            '            <td>' +
            isNull(user.sex) +
            '            <td>' +
            '            <td>' +
            isNull(user.createDate) +
            '            <td>' +
            '        </tr>';
    });
    let html = '<table id="show">' +
        '    <tr>' +
        '        <td>' +
        '            用户名' +
        '        <td>' +
        '        <td>' +
        '            昵称' +
        '        <td>' +
        '        <td>' +
        '            性别' +
        '        <td>' +
        '        <td>' +
        '            创建账号日期' +
        '        <td>' +
        '    </tr>' +
        trtd +
        '</table>';
    return html;
}

function load() {
    // $.ajax()
    // let url = ' http://localhost:8080/crud/ajaxQuery';
    let url = '/crud/ajaxQuery';
    let data = '';
    /*
     $.ajax({
         url: url,
         data: data,
         type: get,
         success: success,
         dataType: dataType
     });
     */
    //$.get(URL,data,function(data,status,xhr),dataType)
    $.get(url, function (result) {
        console.log(result);
        let html = splicingResult(result);
        $("#show").html(html);
    });
}

var userid = '';

function search() {
    let val = $("#search").val();
    let url = '/crud/ajaxQuery';
    console.log('search=', val)
    $.ajax({
        url: url,
        data: {username: val},
        type: 'get',
        dataType: "json",
        success: function (result) {
            let html = splicingResult(result);
            $('#show').html(html);
        }
    });

}

function mdelete() {

}
