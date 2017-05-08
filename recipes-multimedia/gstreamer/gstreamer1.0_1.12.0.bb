require gstreamer1.0.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6762ed442b3822387a51c92d928ead0d \
                    file://gst/gst.h;beginline=1;endline=21;md5=e059138481205ee2c6fc1c079c016d0d"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gstreamer/gstreamer-${PV}.tar.xz \
    file://0001-Make-the-detection-of-libunwind-and-libdw-determinis.patch \
"
SRC_URI[md5sum] = "8f76b6b5e4b3307e505bd6ab9304dd03"
SRC_URI[sha256sum] = "14d5eef8297d2bf2a728d38fa43cd92cc267a0ad260cf83d770215212aff4302"

S = "${WORKDIR}/gstreamer-${PV}"
