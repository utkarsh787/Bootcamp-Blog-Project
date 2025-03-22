(function() {
    "use strict";

    // Initialize any JavaScript functionality for the header
    function initHeader() {
        // Add any JavaScript functionality here
        console.log("Header component initialized");
    }

    // When DOM is ready
    if (document.readyState !== "loading") {
        initHeader();
    } else {
        document.addEventListener("DOMContentLoaded", initHeader);
    }
})();