include gstreamer1.0-omx.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://omx/gstomx.h;beginline=1;endline=21;md5=5c8e1fca32704488e76d2ba9ddfa935f"

SRC_URI = "http://gstreamer.freedesktop.org/src/gst-omx/gst-omx-${PV}.tar.xz"

SRC_URI[md5sum] = "2bca3bd68540a97122450d78576ad2be"
SRC_URI[sha256sum] = "4d43c354087cb77924982943e34e0d47294b5570304db11fd8b268b53bcff693"

S = "${WORKDIR}/gst-omx-${PV}"
