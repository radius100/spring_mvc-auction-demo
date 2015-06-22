<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../LAYOUTS/taglib.jsp"%>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Launch demo modal</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
			</div>
			<div class="modal-body">
				
				<form class="form-horizontal">
					<fieldset>
						<!-- Form Name -->
						<legend>Set new lot</legend>

						<!-- Text input-->
						<div class="control-group">
							<label class="control-label" for="Name">Name</label>
							<div class="controls">
								<input id="Name" name="Name" type="text" placeholder="placeholder" class="input-xlarge" required="">
								<p class="help-block">help</p>
							</div>
						</div>

						<!-- Text input-->
						<div class="control-group">
							<label class="control-label" for="startAmount">startAmount</label>
							<div class="controls">
								<input id="startAmount" name="startAmount" type="text" placeholder="placeholder" class="input-mini">
								<p class="help-block">help</p>
							</div>
						</div>

						<!-- File Button -->
						<div class="control-group">
							<label class="control-label" for="filebutton">Pick Trade Range</label>
							<div class="controls">
								<input id="filebutton" name="filebutton" class="input-file" type="file">
							</div>
						</div>

						<!-- Textarea -->
						<div class="control-group">
							<label class="control-label" for="descr">descr</label>
							<div class="controls">
								<textarea id="descr" name="descr">default tesdxt</textarea>
							</div>
						</div>

						<!-- File Button -->
						<div class="control-group">
							<label class="control-label" for="avatarLoad">Load avatar</label>
							<div class="controls">
								<input id="avatarLoad" name="avatarLoad" class="input-file" type="file">
							</div>
						</div>

						<!-- Multiple Radios -->
						<div class="control-group">
							<div class="controls">
								<label class="radio" for="Publish-0"> <input type="radio" name="Publish" id="Publish-0" value="Publish now" checked="checked"> Publish now
								</label> <label class="radio" for="Publish-1"> <input type="radio" name="Publish" id="Publish-1" value="Pick date"> Pick date
								</label>
							</div>
						</div>

						<!-- File Button -->
						<div class="control-group">
							<label class="control-label" for="filebutton">Pike date (if radio but)</label>
							<div class="controls">
								<input id="filebutton" name="filebutton" class="input-file" type="file">
							</div>
						</div>

					</fieldset>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>
