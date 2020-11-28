<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Send Message</title>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>

<div class="container">
	<div class="row">
		<div class="panel panel-default">
        <div class="panel-heading clearfix">
          <h3 class="panel-title">Message Form</h3>
        </div>
        <div class="panel-body">
            <form role="form" class="form-horizontal">
                <div class="form-group">
                  <label class="col-sm-2" for="inputTo">To</label>
                  <div class="col-sm-10"><input type="email" class="form-control" id="inputTo" placeholder="comma separated list of recipients"></div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2" for="inputSubject">Subject</label>
                  <div class="col-sm-10"><input type="text" class="form-control" id="inputSubject" placeholder="subject"></div>
                </div>
                <div class="form-group">
                  <label class="col-sm-12" for="inputBody">Message</label>
                  <div class="col-sm-12"><ghost><div style="position: absolute; color: transparent; overflow: hidden; white-space: pre-wrap; border: 1.33333px solid rgb(204, 204, 204); border-radius: 4px; box-sizing: border-box; height: 228px; width: 817.333px; z-index: 0; padding: 6px 12px; margin: 0px; top: auto; left: auto; background: none 0% 0% / auto repeat scroll padding-box border-box rgb(255, 255, 255);" data-id="71c8bb8e-8a51-c867-6c4f-5b14b864ac06" data-gramm_id="71c8bb8e-8a51-c867-6c4f-5b14b864ac06" data-gramm="gramm" data-gramm_editor="true" data-grammarly-reactid=".2" contenteditable="true" width="817.3333740234375"><span style="display:inline-block;line-height:26.6667px;color:transparent;overflow:hidden;text-align:left;float:initial;clear:none;box-sizing:border-box;vertical-align:baseline;white-space:pre-wrap;width:100%;margin:0;padding:0;border:0;font:normal normal normal normal 14px / 26.6667px 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size:14px;font-family:'Helvetica Neue', Helvetica, Arial, sans-serif;letter-spacing:normal;text-shadow:none;height:225px;" data-grammarly-reactid=".2.0"></span><br data-grammarly-reactid=".2.1"></div></ghost><textarea class="form-control" id="inputBody" rows="8" data-gramm="true" data-txt_gramm_id="71c8bb8e-8a51-c867-6c4f-5b14b864ac06" data-gramm_id="71c8bb8e-8a51-c867-6c4f-5b14b864ac06" spellcheck="false" data-gramm_editor="true" style="z-index: auto; position: relative; line-height: 26.6667px; font-size: 14px; transition: none; overflow: auto; background: transparent !important;"></textarea><div><div style="z-index: 2; opacity: 1; margin-left: 794px; margin-top: 197px;" class="gr-textarea-btn " data-grammarly-reactid=".3"><div title="Protected by Grammarly" class="gr-textarea-btn_status" data-grammarly-reactid=".3.0"></div></div></div></div>
                </div>
                
                <div style="text-align: center;">
    		<button id="singlebutton" name="singlebutton" class="btn btn-primary">Send</button>
  				</div>
            </form>
        </div>
  		
      </div>
	</div>


</div>
</body>
</html>