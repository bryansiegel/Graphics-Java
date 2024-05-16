<section class="page-section" id="contact" style="background-color: #f9f9f9">
    <div class="container">
        <!-- Contact Section Heading-->
        <h2 class="page-section-heading text-center text-uppercase  mb-5">GAC Work Request</h2>


        <div class="container text-center">
            <div class="row pb-5">
                <div class="col border rounded p-3 m-3 shadow"
                     style="border-top: 5px solid #1671b7 !important; background-color:white;">
                    <p style="color:#1671b7;"><strong>Main Office</strong></p>
                    <p>(702) 799-5380</p>
                    <p>(702) 799-0240 (fax)</p>
                </div>
                <div class="col border rounded p-3 m-3 shadow"
                     style="border-top: 5px solid #1671b7 !important; background-color:white;">
                    <p style="color:#1671b7;"><strong>District Forms</strong></p>
                    <p>(702) 799-5498</p>
                    <p>(702) 799-0240 (fax)</p>
                </div>
                <div class="col border rounded p-3 m-3 shadow"
                     style="border-top: 5px solid #1671b7 !important; background-color:white;">
                    <p style="font-weight:bold; color: #1671b7;">Graphic Arts Center Supervisor</p>
                    <p style="font-weight: bold">Shelly Severin</p>
                    <p>(702) 799-1075 ext.5858</p>
                </div>
            </div>
        </div>


        <!-- Contact Section Form-->
        <div class="row justify-content-center">

            <ul class="errorMessages"></ul>

            <div class="col-lg-8 col-xl-7">

                <form id="contact-form" method="post" action="contact-form-thank-you.php" enctype="multipart/form-data">
                    <!-- Name input-->
                    <div class="form-floating mb-3">
                        <input class="form-control shadow" id="name" name="name" type="text" placeholder="Enter your name..." required
                               oninvalid="this.setCustomValidity('Full Name Is Required')"
                               oninput="this.setCustomValidity('')"
                        />
                        <label for="name">Full name</label>
                    </div>
                    <!-- Email address input-->
                    <div class="form-floating mb-3">
                        <input class="form-control shadow" name="email" id="email" type="email" placeholder="name@example.com" required
                               oninvalid="this.setCustomValidity('Email Is Required')"
                               oninput="this.setCustomValidity('')"
                        />
                        <label for="email">Email address</label>
                    </div>
                    <!-- Phone number input-->
                    <div class="form-floating mb-3">
                        <input class="form-control shadow" name="phone" id="phone" type="tel" placeholder="(123) 456-7890" required
                               oninvalid="this.setCustomValidity('Phone Number Is Required')"
                               oninput="this.setCustomValidity('')"
                        />
                        <label for="phone">Phone number</label>
                    </div>
                    <!--                    Location/Department Name-->
                    <div class="form-floating mb-3">
                        <input class="form-control shadow" name="location" id="location" type="text" placeholder="Location/Department Name" required
                               oninvalid="this.setCustomValidity('Location/Department Name')"
                               oninput="this.setCustomValidity('')"
                        />
                        <label for="location">Location/Department Name</label>
                    </div>
                    <!--                    Project Name-->
                    <div class="form-floating mb-3">
                        <input class="form-control shadow" name="project" id="project" type="text" placeholder="Project Name" required
                               oninvalid="this.setCustomValidity('Project Name Is Required')"
                               oninput="this.setCustomValidity('')"
                        />
                        <label for="project">Project Name</label>
                    </div>
                    <div class="mb-3 p-3 shadow" style="background-color:#d9d8d6; border-radius: 5px;">
                        <p style="font-weight:bold;color:red;">1. Upload Your GAC Work Request Form</p>
                        <label for="attachment"><strong>Upload Your GAC Request Form</strong></label>
                        <input type="file" name="attachment[]" class="form-control shadow" id="attachment" multiple required/>
                    </div>
                    <!--                    Google Drive Link-->
                    <div class="mb-3 p-3 shadow" style="background-color:#d9d8d6; border-radius: 5px;">
                        <p style="font-weight:bold;color:red;">2. Upload Files Releated to your Project (limit 5 files)</p>
                        <label for="attachment"><strong>Upload The Files Related To Your Project</strong></label>
                        <input type="file" name="attachment[]" class="form-control shadow" id="attachment" multiple/>
                    </div>
                    <p style="font-weight:bold;color:red;">If you need to <span style="background-color:red;color:white;padding:5px;">more than 5 files</span>. Please upload the files to Google Drive and insert the share link in the text box below.</p>
                    <div class="form-floating mb-3">
                        <input class="form-control shadow" name="googleDrive" id="googleDrive" type="text" placeholder="Google Drive Link"
                        />
                        <label for="googleDrive">Google Drive Link</label>
                    </div>
                    <!-- Message input-->
                    <div class="form-floating mb-3">
                            <textarea class="form-control shadow" id="message" name="message" type="text"
                                      placeholder="Enter your message here..." style="height: 10rem"></textarea>
                        <label for="message">Message</label>
                    </div>
                    <!-- Submit Button-->
                    <button class="btn btn-primary shadow btn-xl" name="submit" id="submitButton" type="submit">Send</button>
                </form>
            </div>
        </div>
    </div>
</section>
<style>

    input:required:invalid,
    input:focus:invalid {
        background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAeVJREFUeNqkU01oE1EQ/mazSTdRmqSxLVSJVKU9RYoHD8WfHr16kh5EFA8eSy6hXrwUPBSKZ6E9V1CU4tGf0DZWDEQrGkhprRDbCvlpavan3ezu+LLSUnADLZnHwHvzmJlvvpkhZkY7IqFNaTuAfPhhP/8Uo87SGSaDsP27hgYM/lUpy6lHdqsAtM+BPfvqKp3ufYKwcgmWCug6oKmrrG3PoaqngWjdd/922hOBs5C/jJA6x7AiUt8VYVUAVQXXShfIqCYRMZO8/N1N+B8H1sOUwivpSUSVCJ2MAjtVwBAIdv+AQkHQqbOgc+fBvorjyQENDcch16/BtkQdAlC4E6jrYHGgGU18Io3gmhzJuwub6/fQJYNi/YBpCifhbDaAPXFvCBVxXbvfbNGFeN8DkjogWAd8DljV3KRutcEAeHMN/HXZ4p9bhncJHCyhNx52R0Kv/XNuQvYBnM+CP7xddXL5KaJw0TMAF8qjnMvegeK/SLHubhpKDKIrJDlvXoMX3y9xcSMZyBQ+tpyk5hzsa2Ns7LGdfWdbL6fZvHn92d7dgROH/730YBLtiZmEdGPkFnhX4kxmjVe2xgPfCtrRd6GHRtEh9zsL8xVe+pwSzj+OtwvletZZ/wLeKD71L+ZeHHWZ/gowABkp7AwwnEjFAAAAAElFTkSuQmCC);
        background-position: right top;
        background-repeat: no-repeat;
        -moz-box-shadow: none;
    }
    input:required:valid {
        background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAepJREFUeNrEk79PFEEUx9/uDDd7v/AAQQnEQokmJCRGwc7/QeM/YGVxsZJQYI/EhCChICYmUJigNBSGzobQaI5SaYRw6imne0d2D/bYmZ3dGd+YQKEHYiyc5GUyb3Y+77vfeWNpreFfhvXfAWAAJtbKi7dff1rWK9vPHx3mThP2Iaipk5EzTg8Qmru38H7izmkFHAF4WH1R52654PR0Oamzj2dKxYt/Bbg1OPZuY3d9aU82VGem/5LtnJscLxWzfzRxaWNqWJP0XUadIbSzu5DuvUJpzq7sfYBKsP1GJeLB+PWpt8cCXm4+2+zLXx4guKiLXWA2Nc5ChOuacMEPv20FkT+dIawyenVi5VcAbcigWzXLeNiDRCdwId0LFm5IUMBIBgrp8wOEsFlfeCGm23/zoBZWn9a4C314A1nCoM1OAVccuGyCkPs/P+pIdVIOkG9pIh6YlyqCrwhRKD3GygK9PUBImIQQxRi4b2O+JcCLg8+e8NZiLVEygwCrWpYF0jQJziYU/ho2TUuCPTn8hHcQNuZy1/94sAMOzQHDeqaij7Cd8Dt8CatGhX3iWxgtFW/m29pnUjR7TSQcRCIAVW1FSr6KAVYdi+5Pj8yunviYHq7f72po3Y9dbi7CxzDO1+duzCXH9cEPAQYAhJELY/AqBtwAAAAASUVORK5CYII=);
        background-position: right top;
        background-repeat: no-repeat;
    }

</style>