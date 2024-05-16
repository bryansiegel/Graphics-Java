<?php

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require 'PHPMailer/src/Exception.php';
require 'PHPMailer/src/PHPMailer.php';
require 'PHPMailer/src/SMTP.php';


if($_POST) {
    $mail = new PHPMailer;

//
    $full_name = trim($_POST['name']);
    $email = trim($_POST['email']);
    $phone = trim($_POST['phone']);
    $message = trim($_POST['message']);
    $location = trim($_POST['location']);
    $project = trim($_POST['project']);
    $googleDrive = trim($_POST['googleDrive']);


//Preparing Attachments
    for($ct=0;$ct<count($_FILES['attachment']['tmp_name']);$ct++)
    {
        $uploadfile = tempnam(sys_get_temp_dir(), sha1($_FILES['attachment']['name'][$ct]));
        $filename =$_FILES['attachment']['name'][$ct];
        if (move_uploaded_file($_FILES['attachment']['tmp_name'][$ct], $uploadfile)) {
            $mail->addAttachment($uploadfile, $filename);
        }

    }

    $mail->From = '011GraphicArts@nv.ccsd.net';
    $mail->Subject = 'Graphic Arts Center Work Request Submitted by ' . $full_name . ' from ' . $location;
    $mail->addAddress('011GraphicArts@nv.ccsd.net', 'Graphic Arts Center');


    $mail->Body = '<h2>Graphic Arts Center Work Request</h2> 
                <p><b>Name:</b> '.$full_name.'</p> 
                <p><b>Email:</b> '.$email.'</p> 
                <p><b>Phone:</b> '.$phone.'</p> 
                <p><b>Location/Department Name:</b> '.$location.'</p> 
                <p><b>Project Name:</b> '.$project.'</p> 
                <p><b>Google Drive Link:</b> '.$googleDrive.'</p> 
                
                <p><b>Message:</b><br/>'.$message.'</p>';


    $mail->AltBody = '<h2>Graphic Arts Center Work Request</h2> 
                <p><b>Name:</b> '.$full_name.'</p> 
                <p><b>Email:</b> '.$email.'</p> 
                <p><b>Phone:</b> '.$phone.'</p> 
                <p><b>Location/Department Name:</b> '.$location.'</p> 
                <p><b>Project Name:</b> '.$project.'</p> 
                <p><b>Google Drive Link:</b> '.$googleDrive.'</p> 
                
                <p><b>Message:</b><br/>'.$message.'</p>';



    if(!$mail->send()) {
        echo 'Message could not be sent.';
        echo 'Mailer Error: ' . $mail->ErrorInfo;
        exit;
    } ?>

    <!DOCTYPE html>
    <html lang="en">

    <body id="page-top">
    <?php include('includes/head.php'); ?>

    <?php include('includes/nav.php'); ?>

    <section class="page-section center-block">
        <div class="container">
            <div class="row " style="min-height:400px;">

                <h1 class="text-center page-section-heading text-center text-uppercase" style="margin-top:180px;">Your Graphic Arts Center Request Has Been Submitted</h1>
                <h2 class="text-center" style="color:black !important;">Please Allow 24 hours for us to answer your inquiry</h2>
            </div>
        </div>

    </section>

    <?php include('includes/footer.php'); ?>

    <?php include('includes/copywrite.php'); ?>

    <?php include('includes/scripts.php'); ?>


    </body>

    </html>


    <?php
}

else {
    echo '<h1 style="text-align:center;">There has been an error. Please email <a href="mailto:webmaster@ccsd.net">webmaster@ccsd.net</a> for assistance</h1>';
}


?>
