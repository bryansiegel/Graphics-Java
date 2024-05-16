<header class="masthead
<?php switch ($header_color) {
    case "blue":
        echo "masthead-bg-blue ";
        break;
    case "grey":
        echo "masthead-bg-grey ";
        break;
    case "lightblue":
        echo "masthead-bg-lightblue ";
        break;
}
?>bg-primary text-white text-center">
    <div class="container d-flex align-items-center flex-column">
        <h1 class="masthead-heading text-uppercase mb-0 header-top">
            <?php if (isset($header_title) && !empty($header_title)) {
                echo $header_title;
            }
            ?>
        </h1>

        <!-- Masthead Subheading-->
        <p class="masthead-subheading font-weight-light mb-3 text-white">
            <?php if (isset($header_title) && !empty($header_sub_title)) {
                echo $header_sub_title;
            }
            ?>

        </p>

        <div class="btn-toolbar text-center" role="group" aria-label="Header Buttons">
            <center>
                <a href="https://graphicsv2.ccsd.net/#directions" class="btn btn-dark mb-0 pr-2 btn-lg"
                   style="margin-right: 5px; background-color: #0d3a5d;border-color: #0d3a5d;" title="Contact Us"><i
                            class="fa-solid fa-circle-question"></i> How To Submit Work Requests</a>

                <a href="https://graphicsv2.ccsd.net/#graphic_request_forms"
                   class="btn btn-info pr-2 btn-lg"
                   style="color:black; background-color: #c7d6eb;border:1px solid #c7d6eb; margin-right:5px; background-color:white; border:white;"
                   title="Graphics Request Forms"><i class="fa-solid fa-file-word"></i> Work Requests</a>

                <a href="#contact" class="btn btn-dark mb-0 pr-2 btn-lg"
                   style="margin-right: 5px; background-color: #0d3a5d;border-color: #0d3a5d;" title="Contact Us"><i
                            class="fa-solid fa-file-import"></i> Submit Work Requests</a>
            </center>
        </div>

</header>