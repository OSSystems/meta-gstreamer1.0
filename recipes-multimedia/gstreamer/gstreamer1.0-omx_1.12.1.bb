include gstreamer1.0-omx.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://omx/gstomx.h;beginline=1;endline=21;md5=5c8e1fca32704488e76d2ba9ddfa935f"

SRC_URI = "http://gstreamer.freedesktop.org/src/gst-omx/gst-omx-${PV}.tar.xz"

SRC_URI[md5sum] = "eaf9505c7acb9b96215cb0ff775d278d"
SRC_URI[sha256sum] = "acee8c8a88b995fb95601b7b43bf0c83f502d9026bca5705f9c0dc44a757997e"

S = "${WORKDIR}/gst-omx-${PV}"
