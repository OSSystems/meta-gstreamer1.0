include gstreamer1.0-omx.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://omx/gstomx.h;beginline=1;endline=21;md5=5c8e1fca32704488e76d2ba9ddfa935f"

SRC_URI = "http://gstreamer.freedesktop.org/src/gst-omx/gst-omx-${PV}.tar.xz"

SRC_URI[md5sum] = "bec524f2a415af865a895fe12bcf0462"
SRC_URI[sha256sum] = "7cb6c39301dfa3960abce195305c7cf431736bf6b47bde1fd4412c81c3e75cd8"

S = "${WORKDIR}/gst-omx-${PV}"
