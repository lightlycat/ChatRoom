<!DOCTYPE html>
<html>
<head>
    <title>Chat room</title>
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login_app.js"></script>

</head>
<body>


<form id="myAjaxRequestForm">
    <div id="myAjaxRequestFormDiv">
        <fieldset style="border:2px dotted #949989">
            <legend>My Login request</legend>

            <p>
                <label for="customerName" style="margin-left:3px; font: normal 14px Calibri !important;">Login Name:</label>
                <input id="customerName" type="text" name="test" />
                <label for="pwd" style="margin-left:3px; font: normal 14px Calibri !important;">passowrd:</label>
                <input id="pwd" type="text" name="test2" />
                <input id="myButton" type="button" value="Submit" />
            </p>
        </fieldset>
    </div>
</form>

<div id="anotherSection">
    <fieldset style="border:2px dotted #949989">
        <legend>Response from your Request</legend>
        <div id="ajaxResponse"></div>
    </fieldset>
</div>

</body>
</html>