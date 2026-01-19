Current features:

control valkyrien wariums  vehicle control node

make sure to wrap the vehicle control node as a peripheral!

functions:

# pitch            setNodePitch(<1, 0, or -1>)

# yaw              setNodeYaw(<1, 0, or -1>)

# roll             setNodeRoll(<1, 0, or -1>)

# throttle         setNodeThrottle(<-1 to 10>)

# landing gear     setNodeLandingGear(<true/false>)

# channel trigger  setNodeTrigger(<channel>, <true/false>)

# get values   getNode<>() --if trigger than input channel number