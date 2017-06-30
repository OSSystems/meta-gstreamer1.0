require gstreamer1.0.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6762ed442b3822387a51c92d928ead0d \
                    file://gst/gst.h;beginline=1;endline=21;md5=e059138481205ee2c6fc1c079c016d0d"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gstreamer/gstreamer-${PV}.tar.xz \
    file://0001-Make-the-detection-of-libunwind-and-libdw-determinis.patch \
"
SRC_URI[md5sum] = "3c9f2bc7d75daf87fb5d8d0f2158b8ea"
SRC_URI[sha256sum] = "9044b9d8b3ff44457fa9e36faa59c56684b587b34dee449bfe15ec0c32a3a6d2"

S = "${WORKDIR}/gstreamer-${PV}"
